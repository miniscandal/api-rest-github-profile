$requests = @(
    @{ url = "http://127.0.0.1:3001/profiles/miniscandal"; type = "json" },
    @{ url = "http://127.0.0.1:3001/profiles/miniscandals"; type = "json" },
    @{ url = "http://127.0.0.1:3001/profiles/miniscandal/descendant"; type = "json" },
    @{ url = "http://127.0.0.1:3001/profiles_/miniscandal"; type = "json" },
    @{ url = "http://127.0.0.1:3001/profile"; type = "text" }
)

$interval = 5

foreach ($request in $requests) {
    $url = $request.url
    $type = $request.type
    
    $response = curl $url
    
    if ($type -eq "json") {
        $output = $response | ConvertFrom-Json | ConvertTo-Json
    }
    else {
        $output = $response
    }

    Write-Output "Respuesta de ${url} (tipo: ${type}):"
    Write-Output $output
    Write-Output ""

    Start-Sleep -Seconds $interval
}