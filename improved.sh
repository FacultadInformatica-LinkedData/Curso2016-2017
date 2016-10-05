#! /bin/bash

function commit {
echo "I'm in the commit function"
echo "Input your commit"
read commitmssg
echo "Adding folder"
git add .
echo "Making commit"
git commit -m "$commitmssg"
return 0
}

function upstream {
echo "Refreshing the UPSTREAM"
git fetch upstream
if [ $? -eq 0 ]
then
echo "GIT CHECKOUT MASTER"
git checkout master
if [ $? -eq 0 ]
then
echo "GIT MERGE UPSTREAM/MASTER"
git merge upstream/master
fi
fi
return
}

function pusheen {
echo "I'm in the pusheen function"
git push origin master
return
}

if [ $# -eq 0 ]
then
echo "Usage: `basename $0` options"
echo "If you don't want to use the options,
please use the refresh.sh scrip."
exit 2
fi

while getopts ":pcu" opt
do
case $opt in
c)
echo "I should do a commit"
commit
;;
\?)
echo "Invalid option: -$OPTARG"
;;
p)
echo "I should do a PUSHEEN"
pusheen
;;
u)
echo "I should update the UPSTREAM"
upstream
;;
esac
done

echo "Exiting"
exit 0
