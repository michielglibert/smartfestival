For /d %%i IN (*) do (
cd "%%i"
mvnw clean package -DskipTests
cd ..
)
pause