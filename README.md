# Proyecto usando Spring Batch: Asignación de misiones para estudiantes de Jujutsu Kaisen

* Planteamiento del problema a resolver: * En la Academia de Jujutsu Kaisen comunmente se realizan misiones para acabar con maldiciones
  de determinado nivel de energía maldita, para determinar a quiénes se les asignan dichas misiones se tiene que realizar una clasificación
  del tipo de avistamiento y se tiene que documentar para tener todos los registros en orden, como lo importante es atender la misión, los
   hechiceros no tienen el tiempo de estar haciendo esta actividad tan burocrática, por ello se eleaboró esta sencilla aplicación de spring
  Batch que realiza dos paso:

* Step 1.- Lee un archivo .csv que contiene los datos de los avistamientos y los clasifica de acuerdo a la cantidad de energía maldita,
  luego los alamacena en una tabla de SQL.
* Step 2.- Toma los datos actualizados de la tabla SQL y asigna a un hechicero de acuerdo a la categoría del avistamiento, guardando el
  reporte como un documento de una base de datos de MongoDb.

Dentro del código se encontrarán los archivos que forman ambas tablas (Dentro de lacarpeta Model), los processors (dentro de la carpeta processor)
que almacenan la lógica de clasificación y asigación y batchConfig que integra toda la lógica del job y los steps del proceso Batch. 

Realizado por: Juan Pérez Marcelo. 

