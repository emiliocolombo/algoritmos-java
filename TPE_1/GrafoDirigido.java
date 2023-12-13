package TPE_1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ArrayList;
public class GrafoDirigido<T> implements Grafo<T> {
	private HashMap<Integer,HashSet<Integer>> vertices;
	private ArrayList <Arco<T>> arcos;
	
	public GrafoDirigido() {
		this.vertices= new HashMap<Integer,HashSet<Integer>>();
		this.arcos= new ArrayList<Arco<T>>();
	}
	@Override
	//complejidad= O(1) inicializamos las variables de clase
	
	public void agregarVertice(int verticeId) {
		if(!this.vertices.containsKey(verticeId)) {
			this.vertices.put(verticeId,new HashSet<Integer>());
		}
	}
	//complejidad = O(1) hashmap accede a su coleccion mediante clave : valor y esto es O(1)

	@Override
	public void borrarVertice(int verticeId) { 
		this.vertices.remove(verticeId);
		for(int i=0;i<arcos.size();i++) {
			if(arcos.get(i).getVerticeDestino()==verticeId||arcos.get(i).getVerticeOrigen()==verticeId) {
				if(arcos.get(i).getVerticeDestino()==verticeId) {
					this.vertices.get(arcos.get(i).getVerticeOrigen()).remove(verticeId);
				}
				this.arcos.remove(arcos.get(i));
			}
		}
    }
	//complejidad= O(N) donde N es la cantidad de arcos,ya que los recorremos todos, y es el procedimiento
	//con mas costo, por lo tanto el que predomina 
	
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(this.vertices.containsKey(verticeId1)&& this.vertices.containsKey(verticeId2)) {
			Arco<T> nuevoarco= new Arco<T>(verticeId1,verticeId2,etiqueta);
			if(!this.arcos.contains(nuevoarco)) {
		    this.arcos.add(nuevoarco);
		    this.vertices.get(verticeId1).add(verticeId2);
		    }
		}
    }
	//complejidad = O(n) ya que en java, el metodo contains() de 1 Arraylist es O(n), por lo tanto estaremos
	//recorriendo todos los arcos de nuestra estructura.

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		for(int i = 0;i < arcos.size(); i++) {
			if(arcos.get(i).getVerticeOrigen()==verticeId1&& arcos.get(i).getVerticeDestino()==verticeId2) {
				this.arcos.remove(arcos.get(i));
			}
		}
		
	}
	//complejidad = O(N) donde N es la cantidad de arcos de nuestro grafo, y recorremos toda la lista.

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.vertices.containsKey(verticeId);
	}
	//complejidad = O(1), ya que accedemos a un hashmap mediante su clave 

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		for(Arco<T> a:arcos) {
			if(a.getVerticeOrigen()==verticeId1&& a.getVerticeDestino()==verticeId2) {
				return true;
			}
		}
		return false;
	}
	//complejidad = O(N) donde N es la cantidad de arcos de nuestro ArrayList, y recorremos toda la lista 

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		for(Arco<T> a:arcos) {
			if(a.getVerticeOrigen()==verticeId1&& a.getVerticeDestino()==verticeId2) {
				return a;
			}
		}
		return null;
	}
	//complejidad= O(N)  donde N es la cantidad de arcos de nuestro ArrayList, y recorremos toda la lista 

	@Override
	public int cantidadVertices() { 
		return this.vertices.size();
	}
	//complejidad = O(1) ya que size() retorna un atributo de la clase Hashmap.

	@Override
	public int cantidadArcos() {
		return this.arcos.size();
	}
	//complejidad= O(1) ya que size() retorna un atributo de la clase ArrayList.

	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.vertices.keySet().iterator();
	}
	//complejidad= O(1) ya que iterator() crea un iterator y lo devuelve

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		if(this.vertices.containsKey(verticeId)) {
			return this.vertices.get(verticeId).iterator();
		}
		return null;
	}
	//complejidad = O(1) ya que iterator() crea un iterator y lo devuelve

	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		return  this.arcos.iterator();
	}
	//complejidad = O(1) ya que iterator() crea un iterator y lo devuelve

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		ArrayList<Arco<T>> aux= new ArrayList<Arco<T>>();
		for(Arco<T> a:this.arcos) {
			if(a.getVerticeOrigen()==verticeId) {
				aux.add(a);
			}
		}
		return aux.iterator();
	}
	//complejidad = O(N)  donde N es la cantidad de arcos de nuestro grafo, y recorremos toda la lista 
	

}
