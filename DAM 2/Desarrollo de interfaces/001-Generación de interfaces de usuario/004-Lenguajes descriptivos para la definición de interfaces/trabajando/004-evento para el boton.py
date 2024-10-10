import tkinter as tk


def cambiar_color_verde(event):
    event.widget.config(bg="green")


def restaurar_color(event):
    event.widget.config(bg="SystemButtonFace")  

ventana = tk.Tk()

boton = tk.Button(ventana, text="Púlsame", padx=15, pady=15)
boton.pack(padx=40, pady=40)
boton.bind("<Enter>", cambiar_color_verde)  # Cuando el ratón entra
boton.bind("<Leave>", restaurar_color)  # Cuando el ratón sale

ventana.mainloop()