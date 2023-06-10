pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                script{
                    git branch: main, url: 'https://github.com/Rohitmali9293/dotnetsample.git'
                    //modules.msbuild = load "./msbuild.groovy"
                    //modules.msbuild.CleanWorkspace()
                    //echo "workspace cleanup"
                }
            }
        }
        stage('GitCheckout SCM') {
            steps {
                script{
                    modules.msbuild = load "./msbuild.groovy"
                    modules.msbuild.GitCheckout()
                    echo "Git checkout for source code"
                }
            }
        }
        stage('RestorePackages and CleanBuild') {
            steps {
                script{
                    modules.msbuild = load "./msbuild.groovy"
                    modules.msbuild.RestorePackages()
                    modules.msbuild.CleanBuild()
                    echo "Restoring build packges and build new clean pkg"
                }
            }
        }
        stage('IncreaseVersion and Build') {
            steps {
                script{
                    modules.msbuild = load "./msbuild.groovy"
                    modules.msbuild.IncreaseVersion()
                    modules.msbuild.Build()
                    echo "added versioning to new build"
                }
            }
        }
    }
}