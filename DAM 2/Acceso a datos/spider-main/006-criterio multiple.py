import requests
from bs4 import BeautifulSoup
import sqlite3
from urllib.parse import urlparse, urlunparse
import time
import random

def clear_url_parameters(url):
    # Parse the URL into components
    parsed_url = urlparse(url)
    # Reconstruct the URL without query parameters
    cleaned_url = urlunparse((parsed_url.scheme, parsed_url.netloc, parsed_url.path, '', '', ''))
    return cleaned_url


criterios = [
    'web',
    'diseño-web',
    'programacion',
    'html',
    'informática',
    'ordenadores',
    'marketing',
    'aplicaciones',
    'informatico',
    'aplicaciones',
    'desarrollo',
    'soporte-informatico'
    ]
for criterio in criterios:
    pagina = 1
    while pagina < 5:
        
        # URL of the webpage to parse
        url = 'https://www.paginasamarillas.es/search/'+criterio+'/all-ma/valencia/all-is/valencia/all-ba/all-pu/all-nc/'+str(pagina)+'?what='+criterio+'&where=Valencia'

        # Define headers to mimic a Chrome browser on Windows
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36'
        }


        # Send a GET request to the URL with the custom headers
        response = requests.get(url, headers=headers)

        # Check if the request was successful
        if response.status_code == 200:
            # Parse the HTML content using BeautifulSoup
            soup = BeautifulSoup(response.content, 'html.parser')
            
            # Find all <a> tags with class "web"
            a_tags = soup.find_all('a', class_='web')
            img_tags = soup.find_all('img')
            
            # Extract the "target" attribute from each <a> tag
            targets = [a.get('href') for a in a_tags]
            img_targets = [im.get('src') for im in img_tags]

            # Connect to SQLite database (or create it if it doesn't exist)
            conn = sqlite3.connect('targets.db')
            cursor = conn.cursor()
            
            # Create a table if it doesn't exist
            cursor.execute('''
                CREATE TABLE IF NOT EXISTS target_attributes (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    target TEXT NOT NULL
                )
            ''')

            cursor.execute('''
                CREATE TABLE IF NOT EXISTS images (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    source TEXT NOT NULL
                )
            ''')
            
            # Insert each target into the database
            for target in targets:
                cursor.execute('INSERT INTO target_attributes (target) VALUES (?)', (clear_url_parameters(target),))
            
            for image in img_targets:
                cursor.execute('INSERT INTO images (source) VALUES (?)', (image,))
            # Commit the transaction and close the connection
            conn.commit()
            conn.close()
            
            print(f"Successfully saved {len(targets)} target attributes to the database.")
        else:
            print(f"Failed to retrieve the webpage. Status code: {response.status_code}")
        time.sleep(random.randint(2,5))
        pagina += 1
