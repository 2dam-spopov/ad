
public class LanzaHilos {
	public static void main(String[] args) {
		Producto producto = new Producto();
		
		HiloProducir hiloProducir = new HiloProducir("HiloProducir", producto, 12);
		HiloConsumir hiloConsumir = new HiloConsumir("HiloConsumir", producto, 8);
		
		hiloConsumir.setDaemon(true);
		
		hiloProducir.start();
		hiloConsumir.start();
		
		try {
			hiloProducir.join();
		} catch (InterruptedException e) {
			System.out.println("Error al esperar al hilo");
			e.printStackTrace();
		}
		System.out.println("Programa finalizado");
		
	}
}
class HiloProducir extends Thread {

	Producto producto;
	int repeticiones;
	
	public HiloProducir(String nombre, Producto producto, int repeticiones) {
		super(nombre);
		this.producto = producto;
		this.repeticiones = repeticiones;
	}
	
	public void run() {
		for(int i = 0; i < repeticiones + 1; i++) {
			producto.producir();
		}
	}
}
class HiloConsumir extends Thread {

	Producto producto;
	int repeticiones;
	
	public HiloConsumir(String nombre, Producto producto, int repeticiones) {
		super(nombre);
		this.producto = producto;
		this.repeticiones = repeticiones;
	}
	
	public void run() {
		for(int i = 0; i < repeticiones + 1; i++) {
			producto.consumir();
		}
	}
}
class Producto {
	
	int producir = 0;
	int consumir = 0;
	
	boolean contenedor = true;

	public synchronized void producir() {
		while(contenedor) {
				System.out.println("HiloProducir: " + producir);
				producir++;
				notifyAll();
				try {
					//wait();
					wait(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	
	public synchronized void consumir() {
		while(consumir >= producir) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("HiloConsumir: " + consumir);
		consumir++;
		notifyAll();
	}
}