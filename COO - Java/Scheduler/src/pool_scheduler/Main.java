package pool_scheduler;

import pool_scheduler.actions.ActionFinishedException;
import pool_scheduler.actions.Swimmer;
import pool_scheduler.resources.Basket;
import pool_scheduler.resources.BasketPool;
import pool_scheduler.resources.Cubicle;
import pool_scheduler.resources.CubiclePool;
import pool_scheduler.schedulers.FairScheduler;

public class Main {

	public static void main(String[] args) throws ActionFinishedException {
		BasketPool baskets = new BasketPool(6);
		for(int i=0; i<6; i++){
			baskets.addResource(new Basket("basket"));
		}
		CubiclePool cubicles = new CubiclePool(3);
		for(int i=0; i<3; i++){
			cubicles.addResource(new Cubicle("cubicle"));
		}
		FairScheduler s = new FairScheduler();
		
		s.addAction(new Swimmer("Camille", baskets, cubicles, 6, 4, 8));
		s.addAction(new Swimmer("Lois", baskets, cubicles, 2, 10, 4));
		s.addAction(new Swimmer("Maé", baskets, cubicles, 10, 18, 10));
		s.addAction(new Swimmer("Ange", baskets, cubicles, 3, 7, 5));
		s.addAction(new Swimmer("Louison", baskets, cubicles, 18, 3, 3));
		s.addAction(new Swimmer("Charlie", baskets, cubicles, 3, 6, 10));
		s.addAction(new Swimmer("Alexis", baskets, cubicles, 6, 5, 7));
		int nbSteps = 0;
		while (!s.isFinished()) {
			System.out.println(((Swimmer) s.getCurrentAction()).getName() + "'s turn, step " + nbSteps);
			nbSteps++;
			s.doStep();
			
			
		}
		System.out.println("Finished in " + nbSteps + " steps");
	}
}
