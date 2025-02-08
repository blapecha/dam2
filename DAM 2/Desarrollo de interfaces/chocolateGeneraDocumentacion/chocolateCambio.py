import os
import re
import threading
import webbrowser
import chocolate
from tkinter import Tk, StringVar, filedialog, Text, BOTH, END
from ttkbootstrap import ttk, Style
from ttkbootstrap.constants import *

def abrir_archivo(archivo):
    """ Abre el archivo Markdown generado en el navegador predeterminado."""
    if os.path.exists(archivo):
        webbrowser.open(archivo)

def actualizar_vista_previa(archivo, text_widget):
    """ Muestra un resumen del archivo Markdown en un widget de texto."""
    if os.path.exists(archivo):
        with open(archivo, 'r', encoding='utf-8') as f:
            contenido = f.read()
        text_widget.delete(1.0, END)
        text_widget.insert(END, contenido[:1000] + "\n..." if len(contenido) > 1000 else contenido)

def iniciar_proceso(carpeta, archivo_md, actualizar_label, text_widget):
    """ Inicia el procesamiento en un hilo separado para mantener la UI responsiva. """
    def run():
        chocolate.listar_estructura_markdown(carpeta, archivo_md)
        chocolate.agregar_docstrings_markdown(carpeta, archivo_md)
        chocolate.agregar_codigo_markdown(carpeta, archivo_md)
        actualizar_label(f"Proceso completado. Archivo generado: {archivo_md}")
        actualizar_vista_previa(archivo_md, text_widget)
    
    hilo = threading.Thread(target=run)
    hilo.start()

def main():
    root = Tk()
    root.title("Generador de Estructura Markdown")
    root.geometry("800x500")
    style = Style(theme='cosmo')

    ruta_carpeta = StringVar()
    ruta_archivo = StringVar()

    def seleccionar_carpeta():
        carpeta = filedialog.askdirectory()
        if carpeta:
            ruta_carpeta.set(carpeta)

    def seleccionar_archivo():
        archivo = filedialog.asksaveasfilename(defaultextension=".md", filetypes=[("Markdown files", "*.md")])
        if archivo:
            ruta_archivo.set(archivo)

    def actualizar_label(texto):
        estado_var.set(texto)
        root.update_idletasks()

    frame = ttk.Frame(root, padding=20)
    frame.pack(fill=BOTH, expand=True)

    ttk.Label(frame, text="Carpeta de Origen:").grid(row=0, column=0, sticky=W, pady=5)
    ttk.Entry(frame, textvariable=ruta_carpeta, width=50).grid(row=0, column=1, pady=5, padx=5)
    ttk.Button(frame, text="Seleccionar Carpeta", command=seleccionar_carpeta).grid(row=0, column=2, pady=5)

    ttk.Label(frame, text="Archivo de Salida (.md):").grid(row=1, column=0, sticky=W, pady=5)
    ttk.Entry(frame, textvariable=ruta_archivo, width=50).grid(row=1, column=1, pady=5, padx=5)
    ttk.Button(frame, text="Seleccionar Archivo", command=seleccionar_archivo).grid(row=1, column=2, pady=5)

    procesar_button = ttk.Button(frame, text="Iniciar Proceso", command=lambda: iniciar_proceso(
        ruta_carpeta.get(), ruta_archivo.get(), actualizar_label, vista_previa))
    procesar_button.grid(row=2, column=1, pady=20)

    estado_var = StringVar()
    estado_var.set("Esperando para iniciar...")
    ttk.Label(frame, textvariable=estado_var, bootstyle="info").grid(row=3, column=0, columnspan=3, pady=10)

    vista_previa = Text(frame, height=10, wrap='word', state=NORMAL)
    vista_previa.grid(row=4, column=0, columnspan=3, pady=10, sticky=W+E)

    abrir_button = ttk.Button(frame, text="Abrir Archivo", command=lambda: abrir_archivo(ruta_archivo.get()))
    abrir_button.grid(row=5, column=1, pady=5)

    frame.columnconfigure(1, weight=1)

    root.mainloop()

if __name__ == "__main__":
    main()
