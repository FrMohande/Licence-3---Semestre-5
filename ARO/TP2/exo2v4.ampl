set MOYEN;
param prix{MOYEN} >= 0;
param impact{MOYEN} >= 0;
param budget >= 0;
param conMIN{MOYEN} >= 0;
param perso_req{MOYEN} >= 0;
param maxper >= 0;


var nb_min_page{m in MOYEN} >= conMIN[m];

maximize impacte : 
sum {m in MOYEN} impact[m] * nb_min_page[m];

subject to cbudget :
sum {m in MOYEN} 
(prix[m] * nb_min_page[m]) <= budget;

subject to personnes :
sum {m in MOYEN} (nb_min_page[m] * perso_req[m]) <= maxper;

data;
set MOYEN := TV magazine radio;
param budget = 100;
param maxper = 100;
param: prix impact conMIN perso_req :=
TV 2 1.8 10 1
magazine 1 1 0 3
radio 0.2 0.205 120 0.2;
