@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n11_conejera
REM Autor: Juan David Villa - 03-oct-2009
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------
cd ..
java -ea -classpath ./lib/conejera.jar;./test/lib/conejeraTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.conejera.test.ConejeraTest
java -ea -classpath ./lib/conejera.jar;./test/lib/conejeraTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.conejera.test.ParejaConejosTest
java -ea -classpath ./lib/conejera.jar;./test/lib/conejeraTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.conejera.test.IdParejaConejosTest
pause