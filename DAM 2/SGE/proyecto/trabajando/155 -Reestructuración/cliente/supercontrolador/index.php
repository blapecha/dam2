<!doctype html>
<html lang="es">
  <head>
    <title>jocarsa | crimson</title>
    <meta charset="utf-8">
    <script src="lib/selectjv/selectjv.js"></script>
    <link rel="Stylesheet" href="./lib/selectjv/selectjv.css">
    <link rel='icon' type='image/svg+xml' href='https://jocarsa.com/static/logo/jocarsa | crimson.svg' />
    <link rel="stylesheet" href="estilo.css">
    <script src="js/funciones.js"></script>
    <script src="js/convierteTipoDato.js"></script>
    <script src="js/poblarMenuNavegacion.js"></script>
    <script src="js/pueblaTabla.js"></script>
    <script src="js/cargaDatosTabla.js"></script>
    <script src="js/cargaDatosColeccion.js"></script>
    <script src="comportamiento.js"></script>
    <script src="lib/JVGrafica/JVGrafica.js"></script>
  </head>
  <body>
    <header>
      <h1><img src="https://jocarsa.com/static/logo/jocarsa | white.svg">jocarsa | crimson</h1>
      <div class="titulotabla">
          <h5>Clientes</h5>
          <p>En esta tabla podremos insertar a los clientes</p>
        </div>
      <nav>
      <button class="boton botonblanco" id="imprimir"><img src="img/iconoimprimir.svg"></button>
      	<button class="boton botonblanco" id="mail"><img src="img/iconocorreo.svg"></button>
      	<button class="boton botonblanco">A</button>
      	<button class="boton botonblanco">A</button>
      	<button class="boton botonblanco">A</button>
      	<button class="boton botonblanco">A</button>
      	<button class="boton botonblanco">A</button>
      	<button class="boton botonblanco" id="ayuda">Ayuda</button>
      </nav>
    </header>
    <main>
      <nav>
      	<ul>
      	<h4>Aplicaciones</h4>
      		<li id="businessanalytics"><span class='boton botonblanco'><img src='img/iconotabla.svg'></span> Business Analytics</li>
      	</ul>
        <ul id="tabla">
          <h4>Tablas</h4>
        </ul>
        
        <ul id="coleccion">
        <h4>Documentos</h4>
        </ul>
        <ul>
        	<li id="escritorio"><span class='boton botonblanco'><img src='img/iconotabla.svg'></span> Volver al escritorio</li>
        </ul>
      </nav>
      <section>
      	<div class="consola">SELECT * FROM clientes;<span id="businessanalytics">🤖</span></div> <!-- Candidato para modulo -->
        <button id="insertar">+</button>
        
        <table>
          <thead>
            <tr>  
            </tr>
          </thead>
          <tbody> 
          </tbody>
        </table>
      </section>
    </main>
    <footer>(c) jocarsa | crimson</footer>
    <div id="modal">
      <div id="contienemodal"></div>
    </div>    <!-- Es una ventana emergente que podemos usar para lo que queramos -->
      <link rel="stylesheet" href="./lib/jvampliador/jvampliador.css">
		<script src="./lib/jvampliador/jvampliador.js"></script>
		<script src="./lib/jvtooltip/jvtooltip.js"></script>
		<link rel="stylesheet" href="./lib/jvtooltip/jvtooltip.css">
		<script src="./lib/jvwysiwyg/jvwysiwyg.js"></script>
		<link rel="stylesheet" href="./lib/jvwysiwyg/jvwysiwyg.css">
		<script src="./lib/jvtabla/jvtabla.js"></script>
		<link rel="stylesheet" href="./lib/jvtabla/jvtabla.css">
  </body>
 
</html>
