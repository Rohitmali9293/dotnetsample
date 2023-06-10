//workspace cleanup plugin required for this step
def CleanWorkspace() {
    cleanWs()
}

//for now hardcoding creds 
def GitCheckout() {
    git branch: main, url: 'https://github.com/Rohitmali9293/dotnetsample.git'
}

//using NuGet package manager
def RestorePackages() {
    bat "dotnet restore ${workspace}\\<project-path>\\<project-name>.sln"
}

//clean build using MSBuild.exe
//nologo= Don't display the startup banner or the copyright message. 
//nr:false= Nodes don't remain after the build completes
//p:configuration=\"release\" = Set configuration to release
//t:clean= clean build

def CleanBuild() {
    bat "msbuild.exe ${workspace}\\<project-path>\\<project-name>.sln" /nologo /nr:false /p:configuration=\"release\" /t:clean
}

// increase the applications version
def IncreaseVersion() {
    echo "${env.BUILD_NUMBER}"
    powershell '''
        $xmlFileName = "<project-path>\\<project-name>\\Package.appxmanifest"     
        [xml]$xmlDoc = Get-Content $xmlFileName
        $version = $xmlDoc.Package.Identity.Version
        $trimmedVersion = $version -replace '.[0-9]+$', '.'
        $xmlDoc.Package.Identity.Version = $trimmedVersion + ${env:BUILD_NUMBER}
        echo 'New version:' $xmlDoc.Package.Identity.Version
        $xmlDoc.Save($xmlFileName)
    '''
}

def Build() {
    bat "msbuild.exe ${workspace}\\<project-path>\\<project-name>.sln /nologo /nr:false /p:configuration=\"release\" /t:clean;restore;rebuild"
}