import tkinter as tk

ventana = tk.Tk()

def diHola():
    print("Hola")
    
def diAdios():
        print("Adios")

tk.Button(ventana,text="Bienvenido",padx=15,pady=15,command=diHola).pack(padx=40,pady=40)
tk.Button(ventana,text="Despedida",padx=15,pady=15,command=diAdios).pack(padx=40,pady=40)

ventana.mainloop()
