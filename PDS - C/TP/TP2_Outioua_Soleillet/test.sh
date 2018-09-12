#!/bin/bash
make realclean all
#Test sur le Répertoire 
echo "test 1 :: repertoire normal"
./mdu test_dir/test1/
echo "test avec du -B 512 ::" 
du -B 512 test_dir/test1/
echo
./mdu -b test_dir/test1/
echo "test avec du -b ::" 
du -b test_dir/test1/
echo

#Test avec un lien symbolique tutu -> titi/toto
echo "test 2 :: avec un lien symbolique tutu-> titi/toto"
./mdu -L test_dir/test2
echo "test avec du -L -B 512 ::" 
du -L -B 512 test_dir/test2/
echo
./mdu -Lb test_dir/test2
echo "test avec du -L -b ::"
du -L -b .
echo

#test avec un lien symbolique sur lui même link -> link
echo "test 2 :: avec un lien symbolique qui pointe sur lui même link->link"
echo "mdu -L"
./mdu -L test_dir/test3
echo "mdu -Lb"
./mdu -Lb test_dir/test3
echo "mdu -b"
./mdu -b test_dir/test3
echo "du -L"
du -L test_dir/test3
echo "du -Lb"
du -Lb test_dir/test3
echo "du -b"
du -b test_dir/test3
echo

#test avec un lien symbolique qui pointe sur le dossier courrant  jiji-> .
echo "test 4 :: avec un lien symbolique qui pointe sur le dossier courant  jiji->."
echo "mdu -L ::"
./mdu -L test_dir/test4
echo "du -L -B 512 ::"
du -L -B 512 test_dir/test4

echo "mdu -Lb ::"
./mdu -Lb test_dir/test4
echo "du -L ::"
du -L -b test_dir/test4

echo "mdu -b ::"
./mdu -b test_dir/test4
echo "du -b ::"
du -b test_dir/test4

echo "mdu ::"
./mdu test_dir/test4
echo "du -B 512"
du -B 512 test_dir/test4
echo


#test avec un lien symbolique qui pointe sur dossier qui n'existe pas
# toto -> test/toto
echo "test 5 :: avec un lien symbolique qui pointe sur un repértoire qui n'existe pas  toto -> test/toto "


echo "mdu -L"
./mdu -L test_dir/test5
echo "du -L -B 512"
du -L -B 512 test_dir/test5

echo "mdu -Lb"
./mdu -Lb test_dir/test5
echo "du -L"
du -L test_dir/test5

echo "mdu -b"
./mdu -b test_dir/test5
echo "du -b"
du -b test_dir/test5

echo "mdu"
./mdu test_dir/test5
echo "du -B 512"
du -B 512 test_dir/test5









