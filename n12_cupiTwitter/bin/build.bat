@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogot� - Colombia)
REM Departamento de Ingenier�a de Sistemas y Computaci�n 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n12_cupiTwitter
REM Autor: Camilo Alvarez Duran - 19-oct-2009
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Asegura la creaci�n de los directorios classes y lib
REM ---------------------------------------------------------

cd ..
mkdir classes
mkdir lib

REM ---------------------------------------------------------
REM Compila las clases del directorio source
REM ---------------------------------------------------------
cd source
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiTwitter/cliente/mundo/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiTwitter/interfazCliente/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiTwitter/servidor/mundo/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiTwitter/interfazServidor/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiTwitter/comun/mundo/*.java

REM ---------------------------------------------------------
REM Crea el archivo jar a partir de los archivos compilados
REM ---------------------------------------------------------

cd ../classes
jar cf ../lib/cupiTwitter.jar uniandes/*

cd ../bin

pause
