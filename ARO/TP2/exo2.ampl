set MOYEN;
param prix{MOYEN} >= 0;
param impact{MOYEN} >= 0;
param budget >= 0;
param conMIN{MOYEN} >= 0;

var nb_min_page{m in MOYEN} >= conMIN[m];

maximize impacte : 
sum {m in MOYEN} impact[m] * nb_min_page[m];

subject to cbudget :
sum {m in MOYEN} 
(prix[m] * nb_min_page[m]) <= budget;

data;
set MOYEN := TV magazine;
param budget = 100;
param: prix impact conMIN :=
TV 2 1.8 10
magazine 1 1 0;

