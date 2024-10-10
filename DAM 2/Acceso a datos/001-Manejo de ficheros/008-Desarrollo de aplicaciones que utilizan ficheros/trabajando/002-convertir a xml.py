import json
import xml.etree.ElementTree as ET

# Función para convertir un diccionario en un elemento XML
def dict_to_xml(tag, d):
    # Crea un elemento raíz con el nombre especificado en 'tag'
    elem = ET.Element(tag)
    # Recorre cada clave y valor en el diccionario
    for key, val in d.items():
        # Crea un subelemento para cada clave, con el nombre de la clave
        child = ET.SubElement(elem, key)
        # Asigna el valor como texto del subelemento, convirtiéndolo a string
        child.text = str(val)
    # Devuelve el elemento raíz con todos sus subelementos
    return elem

# Función para guardar un diccionario como un archivo XML
def save_dict_to_xml(filename, root_tag, dictionary):
    # Convierte el diccionario a XML usando dict_to_xml
    root = dict_to_xml(root_tag, dictionary)
    # Crea un árbol XML con el elemento raíz
    tree = ET.ElementTree(root)
    # Escribe el árbol XML en un archivo con codificación UTF-8 y declaración XML
    tree.write(filename, encoding='utf-8', xml_declaration=True)

# Abre y lee el archivo JSON
with open('cliente.json', 'r') as archivo:
    # Carga el contenido del archivo JSON como un diccionario
    datos = json.load(archivo)

# Imprime los datos del archivo JSON en la consola
print(datos)

# Guarda los datos en un archivo XML, usando 'cliente' como etiqueta raíz
save_dict_to_xml('cliente.xml', 'cliente', datos)





