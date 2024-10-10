import tkinter as tk
from tkinter import filedialog

class MiVentana(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Ventana para videos")  # Título de la ventana
        self.geometry("300x200")  # Tamaño de la ventana
        self.config(bg="lightblue")  # Color de fondo de la ventana

        # Marco donde colocarás los widgets
        marco = tk.Frame(self)
        marco.pack(padx=10, pady=10)

        # Botón para seleccionar el archivo de video
        selector_video_entrada = tk.Button(
            marco,
            text="Selecciona el video de entrada",
            command=self.selecciona_entrada
        )
        selector_video_entrada.pack(padx=50, pady=50)

    def selecciona_entrada(self):
        ruta = filedialog.askopenfilename()
        if ruta:  # Si se seleccionó un archivo, mostrar la ruta
            print(f"Archivo seleccionado: {ruta}")

# Crear una instancia de MiVentana y ejecutar el loop principal
ventana = MiVentana()
ventana.mainloop()

