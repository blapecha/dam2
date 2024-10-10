import tkinter as tk
from tkinter import filedialog
import os

ventana = tk.Tk()

marco = tk.Frame(ventana)
marco.pack(padx=10, pady=10)

def seleccionaEntrada():
 
    carpeta = filedialog.askdirectory()       # Abre un cuadro de diálogo para seleccionar una carpeta
    if carpeta:                                # Si se seleccionó una carpeta
        listar_videos(carpeta)

def listar_videos(carpeta):
                                            # Lista de extensiones de video comunes
    extensiones_video = ('.mp4', '.avi', '.mov', '.mkv', '.flv', '.wmv')
    videos = []

    # Recorrer los archivos en la carpeta seleccionada
    for archivo in os.listdir(carpeta):
        if archivo.lower().endswith(extensiones_video):  # Filtrar archivos de video
            videos.append(archivo)

    # Mostrar los nombres de los videos
    if videos:
        print("Videos encontrados:")
        for video in videos:
            print(video)
    else:
        print("No se encontraron videos en la carpeta.")

# Botón para seleccionar la carpeta de videos
selector_video_entrada = tk.Button(
    marco,
    text="Selecciona la carpeta de videos",
    command=seleccionaEntrada
)

selector_video_entrada.pack(padx=50, pady=50)

ventana.mainloop()

