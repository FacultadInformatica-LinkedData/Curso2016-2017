Course 2016-2017
================

This is our repository that we will use for our collaborative work and for uploading all the assignments for course 2016-2017

This is the normal process that you will have to follow:

* Fork the main repository into your own account (this will generate a new repository in your GitHub account). This is done only once during the course. 
* If you had already forked the repository some time ago, you may want to sync your repository to the latest version that is now available. This is done by [configuring the remote for a fork](https://help.github.com/articles/configuring-a-remote-for-a-fork) and [syncing your fork](https://help.github.com/articles/syncing-a-fork). Basically, you have to:
 * Establish remote: 
 
        git remote add upstream https://github.com/FacultadInformatica-LinkedData/Curso2016-2017

 * Fetch any changes to it: 
 
        git fetch upstream
 
 * Checkout the local master branch of your fork: 
 
        git checkout master
 
 * Merge changes from the remote into your master branch: 
 
        git merge upstream/master

* Make any changes to your repository, according to the specific assignment
* Commit your changes into your local repository
* Push your changes to your online repository
* Make a pull request, so that we can check your changes and accept them into the master of the general repository. If everything is ok, your changes will be incorporated into the main repository. If not, you will receive a message of why not.

**Assignment 1**. Please fill in a line with a dataset description at folder [Assignment 1](./Assignment1/DatasetDescriptions.csv), as discussed in the course moodle site, and make a pull request

