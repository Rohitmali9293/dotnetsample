#!/usr/bin/env groovy
pipeline {
    agent any
    stages {
        stage('Clean') {
            steps {
                checkout scm
                    //modules.msbuild = load "./msbuild.groovy"
                    //modules.msbuild.CleanWorkspace()
                    //echo "workspace cleanup"
            }
        }
        stage('GitCheckout SCM') {
            steps {
                bat '''
                msbuild ConsoleApp.sln /p:Configuration=Release
                '''
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
