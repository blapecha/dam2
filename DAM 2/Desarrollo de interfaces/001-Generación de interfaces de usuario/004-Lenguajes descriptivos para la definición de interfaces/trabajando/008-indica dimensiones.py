import tkinter as tk
from tkinter import filedialog
from moviepy.editor import VideoFileClip

ventana = tk.Tk()

marco = tk.Frame(ventana)
marco.pack(padx=10, pady=10)

def seleccionaEntrada():
    ruta = filedialog.askopenfilename()
    if ruta:  # Si se seleccionó un archivo
        print("La ruta de tu video es:", ruta)
        mostrar_duracion(ruta)  # Llama a la función para mostrar la duración

def mostrar_duracion(ruta):
    try:
        # Abre el video y obtiene la duración
        video = VideoFileClip(ruta)
        duracion = video.duration  # Duración en segundos
        # Cierra el video para liberar recursos
        video.close()
        # Muestra la duración en minutos y segundos
        minutos = int(duracion // 60)
        segundos = int(duracion % 60)
        print(f"La duración del video es de: {minutos} minutos y {segundos} segundos.")
    except Exception as e:
        print("Error al obtener la duración del video:", e)

selector_video_entrada = tk.Button(
    marco,
    text="Selecciona el video de entrada",
    command=seleccionaEntrada
)
selector_video_entrada.pack(padx=50, pady=20)

tk.Label(
    marco,
    text="Indica la duración del video"
).pack(padx=50, pady=20)

ventana.mainloop()
