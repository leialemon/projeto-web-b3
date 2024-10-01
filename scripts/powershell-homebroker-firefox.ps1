Set-Location -Path "..\miniFront"

Start-Process "firefox.exe" "index.html"

Set-Location -Path "..\homebroker\target"

Start-Process "java.exe" "-jar homebroker-0.0.1-SNAPSHOT.jar"

