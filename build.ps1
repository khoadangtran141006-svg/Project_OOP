$ErrorActionPreference = 'Stop'
Set-Location $PSScriptRoot

# Clean stale compiler output from the project and output folder.
Get-ChildItem -LiteralPath . -File -Filter '*.class' -Recurse | Remove-Item -Force

New-Item -ItemType Directory -Force -Path 'bin' | Out-Null
$sources = @('main.java')
foreach ($folder in @('doituong', 'danhsach', 'menu')) {
    $sources += Get-ChildItem -LiteralPath $folder -File -Filter '*.java' | ForEach-Object {
        Join-Path $folder $_.Name
    }
}

& javac -encoding UTF-8 -Xlint:all -d bin $sources
if ($LASTEXITCODE -ne 0) { throw "javac failed with exit code $LASTEXITCODE" }

& java -cp bin main
if ($LASTEXITCODE -ne 0) { throw "java exited with code $LASTEXITCODE" }
