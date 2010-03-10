@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n9_cupiOca
REM Autor: Juan David Villa - 14-oct-2009
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------
cd ..
java -ea -classpath ./lib/cupiOca.jar;./test/lib/cupiOcaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiOca.test.CupiOcaTest
java -ea -classpath ./lib/cupiOca.jar;./test/lib/cupiOcaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiOca.test.CasillaTest
java -ea -classpath ./lib/cupiOca.jar;./test/lib/cupiOcaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiOca.test.InfoJugadaTest
java -ea -classpath ./lib/cupiOca.jar;./test/lib/cupiOcaTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiOca.test.JugadorTest