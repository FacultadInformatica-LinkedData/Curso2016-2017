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

**Assignment 2 - Exercise on RDF and RDFS**. Upload the RDF and RDFS files corresponding to the exercise in the GitHub repository, and comment on those from your colleagues, creating issues in the GitHub repository. This can be done in the [Assignment2](./Assignment2/) folder, **using your GitHub username** + (.png/.svg) and (.rdf/.ttl), or any other similar file extension.

For example, I (my Github username is 'nandana') will upload three files :
* nandna.png (the image, Exercise 1.a) 
* nandana.rdf (Exercise 1.b in RDF/XML), and 
* nandana.ttl (Exercise 1.b in Turtle).

**Assignment 3 - Exercise on Jena API**. Upload the java files Task06.java and Task07.java after completing the tasks 6.x and 7.x mentioned in those files. Before uploading the files create a folder with your **YourGitHubID-StudentID** (e.g., Assignment3/nandana-u120106/) in [Assignment3](./Assignment3/) folder.

**Assignment 4 - SPARQL**. Upload the SPARQL queries and their results after completing the completing the tasks mentioned in the slides. Before uploading the files create a folder with your **YourGitHubID-StudentID** (e.g., Assignment3/nandana-u120106/) in [Assignment4](./Assignment4/) folder.
