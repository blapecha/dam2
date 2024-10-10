import tkinter as tk

ventana = tk.Tk()


def ventana_creada():
    print("La ventana ha sido creada correctamente.")

ventana.after(100, ventana_creada)
   
ventana.mainloop()
