import xml.etree.ElementTree as ET


arbol = ET.parse('013-interfaz.xml') 
raiz = arbol.getroot()

for elemento in raiz:
    if elemento.tag == "boton":
        print("tienes un boton")
    elif elemento.tag == "texto":
        print("tienes un texto")
    elif elemento.tag == "entrada":
        print("tienes una entrada")
    elif elemento.tag == "salida":
        print("tienes una salida")
    elif elemento.tag == "a":
        for camino in elemento:
            print(camino.text)
