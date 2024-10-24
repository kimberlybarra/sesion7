package Juego;

public class Personaje {
	private String nombre;
	private int vida;
	private int ataque;
	private int defensa;
	private int alcance;
	
	public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
	if(vida <= 0 || ataque <= 0 || defensa <= 0 || alcance <= 0) {
		throw new IllegalArgumentException ("Los datos del personaje no pueden ser cero ni negativos, intente otra vez");
	}
	this.nombre = nombre;
	this.vida = vida;
	this.ataque = ataque;
	this.defensa = defensa;
	this.alcance = alcance;
	}
	
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        if (vida > 0) this.vida = vida;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        if (ataque > 0) this.ataque = ataque;
    }
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        if (defensa > 0) this.defensa = defensa;
    }
    public int getAlcance() {
        return alcance;
    }
    public void setAlcance(int alcance) {
        if (alcance > 0) this.alcance = alcance;
    }
    
    @Override
    public String toString() {
        return nombre + "," + vida + "," + ataque + "," + defensa + "," + alcance;
    }
}
