# <center> TP ALGORITMOS GENÉTICOS</center>


Para poder ejecutar el proyecto, se debe contar con Python y Java en su computadora. 

## Instrucciones de compilación

A continuación, se mencionan una serie de comandos que permitirán compilar el proyecto y generar el archivo de tipo JAR que usaremos luego. 

 1.  **Posicionarse desde la terminal en el root del proyecto**
 2.  **Ejecutar el siguiente comando para compilar el programa en Java:**
		 `make all` 

## Instrucciones de ejecución
A continuación, se menciona el comando a ejecutar para correr el proyecto. 

`java -jar .\out\artifacts\RPG_jar\RPG.jar | py .\src\main\python\graphics.py`
ó
`./start_program.bat`

> **Aclaración:** Notar que el archivo **start_program.bat** funciona para el sistema operativo **Windows** ya que utiliza sus respectivas barras invertidas ("\\"). 

Con este comando se corre el programa de Java y el programa de Python en simultáneo. La salida del programa de Java se redirige hacia la entrada estándar del programa de Python, el cual recibe estos datos y los muestra en forma de gráfico (utilizando la librería Matplotlib).
  

## Instrucciones para configurar el JSON
| CAMPO                   | VALOR  | DETALLE                                                                                                                                                                       |
| ----------------------- | ------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| maxLines                | int    | La cantidad de lineas que va a leer por cada archivo                                                                                                                          |
| weaponsPath             | string | El path donde esta ubicado el archivo de weapons                                                                                                                              |
| helmetsPath             | string | El path donde esta ubicado el archivo de helmets                                                                                                                              |
| glovesPath              | string | El path donde esta ubicado el archivo de gloves                                                                                                                               |
| bootsPath               | string | El path donde esta ubicado el archivo de boots                                                                                                                                |
| armorsPath              | string | El path donde esta ubicado el archivo de armors                                                                                                                               |
| playerClass             | string | La clase del jugador.<br>Valores posibles: WARRIOR, ARCHER, DEFENDER, ROGUE                                                                                                   |
| firstSelectionMethod    | string | Método de selección.<br>Los valores posibles son: ELITE, ROULETTE, RANKING, UNIVERSAL, PROBABILISTIC\_TOURNAMENT, DETERMINISTIC\_TOURNAMENT                                   |
| secondSelectionMethod   | string | Método de selección.<br>Los valores posibles son: ELITE, ROULETTE, RANKING, UNIVERSAL, PROBABILISTIC\_TOURNAMENT, DETERMINISTIC\_TOURNAMENT                                   |
| A                       | double | Porcentaje utilizado para determinar cantidad de padres que se van a elegir con el primer método de selección (el resto se elije con el método 2). (va entre 0 y 1).          |
| K                       | int    | Cantidad de padres que se van a elegir                                                                                                                                        |
| firstReplacementMethod  | string | Método de reemplazo. Los valores posibles son: ELITE, ROULETTE, RANKING, UNIVERSAL, PROBABILISTIC\_TOURNAMENT, DETERMINISTIC\_TOURNAMENT                                      |
| secondReplacementMethod | string | Método de reemplazo. Los valores posibles son: ELITE, ROULETTE, RANKING, UNIVERSAL, PROBABILISTIC\_TOURNAMENT, DETERMINISTIC\_TOURNAMENT                                      |
| B                       | double | Porcentaje utilizado para determinar cantidad de hijos que se van a reemplazar con el primer método de reemplazo (el resto se elije con el método 2). (va entre 0 y 1).       |
| N                       | int    | Cantidad de hijos que se van a reemplazar                                                                                                                                     |
| deterministicM          | int    | Cantidad de individuos al azar que se van a elegir en torneos deterministicos                                                                                                 |
| crossover               | string | Recombinación de genes.<br>Los valores posibles son: SINGLE\_POINT, DOUBLE\_POINT, ANNULAR, UNIFORM                                                                           |
| mutation                | string | Variación en la información genetica que se almacena en el cromosoma.<br>Los valores posibles son: SIMPLE\_MUTATION, LIMITED\_MULTIGEN, UNIFORM\_MULTIGEN, COMPLETE\_MUTATION |
| Pm                      | double | Probabilidad de mutación (va entre 0 y 1).                                                                                                                                    |
| implementation          | string | La implementación que se utiliza para generar K hijos de K padres.<br>Los valores posibles son: FILL\_PARENT, FILL\_ALL                                                       |
| initialPopulation       | int    | La población inicial con la que se cuanta al iniciar el programa. (optamos porque la misma se elija al azar)                                                                  |
| evaluator               | string | Criterio de corte.<br>Los valores posibles son: TIME, GEN\_Q, CONTENT, STRUCTURE, ACCEPTABLE\_SOLUTION                                                                        |
| maxGenerations          | int    | La cantidad máxima de generaciones (para corte GEN\_Q).                                                                                                                       |
| acceptableSolution      | int    | Máximo fitness de la solución aceptable (para el corte de ACCEPTABLE\_SOLUTION).                                                                                              |
| maxMillis               | long   | Cantidad máxima de milisegundos para el corte del programa (para el corte TIME).                                                                                              |
| contentMaxRounds        | int    | Cantidad máxima de rondas a esperar a que cambie el máximo performance (para el corte CONTENT).                                                                               |
| structureVariety        | double | Diversidad genética mínima aceptable (varía entre 0 y 1) (corte STRUCTURE).                                                                                                   |
| delta                   | double | Variación máxima aceptable para decir que dos items son similares (corte STRUCTURE).                                                                                          |


## EQUIPO

A continuación, los miembros del equipo que hicieron este proyecto posible:
>IGNACIO  SAMPEDRO

> MARTIN CICCIOLI

> GERONIMO MASPERO




