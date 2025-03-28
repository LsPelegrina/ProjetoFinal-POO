// Nome: Lucas de Souza Pelegrina - RA:2465310
package com.mycompany.poo.projetofinal;
import javax.swing.JOptionPane;

public class Kitnet extends Imovel implements Calculos{

	private boolean compartilhada;
	private int moradores;

	public Kitnet(){
            compartilhada = false;
            moradores = 0;
	}

// ============= SOBRECARGA ==============
	public Kitnet(boolean compartilhada, int moradores){
            this.compartilhada = compartilhada;
            this.moradores = moradores;
	}
	public Kitnet(Kitnet kn){
            super(kn);
            this.compartilhada = kn.compartilhada;
            this.moradores = kn.moradores;
	}

// ============= SOBRESCRITA =============
	public final double contAluguel(){
            double base = 350.0;
            base+=(getQuarto()*500.0);
            base+=(getBanheiro()*350.0);
            base+=(getArea()*15.0);
            if(getMobilia())
                base+=500.0;
            base = ((base/2)-(moradores*200));
            if(getLocal().getReg().getUf().equals("SP") || getLocal().getReg().getUf().equals("RJ") || getLocal().getReg().getUf().equals("BA") || getLocal().getReg().getUf().equals("DF") || getLocal().getReg().getUf().equals("RS") || getLocal().getReg().getUf().equals("SC"))
                base*=1.5;
            else	if(getLocal().getReg().getUf().equals("MG") || getLocal().getReg().getUf().equals("ES") || getLocal().getReg().getUf().equals("MS") || getLocal().getReg().getUf().equals("GO") || getLocal().getReg().getUf().equals("SE") || getLocal().getReg().getUf().equals("PR") || getLocal().getReg().getUf().equals("CE") || getLocal().getReg().getUf().equals("PE") || getLocal().getReg().getUf().equals("AM") || getLocal().getReg().getUf().equals("AM") || getLocal().getReg().getUf().equals("AM"))
                base*=1.25;
            else
                base*=1;
            return base+getContrato().getImposto();
	}
	public final double contCompra(){
            double base = 60000.0;
            base+=(getQuarto()*30000.0);
            base+=(getBanheiro()*20000.0);
            base+=(getArea()*1000.0);
            if(getMobilia())
                base+=12500.0;
            if(getLocal().getReg().getUf().equals("SP") || getLocal().getReg().getUf().equals("RJ") || getLocal().getReg().getUf().equals("BA") || getLocal().getReg().getUf().equals("DF") || getLocal().getReg().getUf().equals("RS") || getLocal().getReg().getUf().equals("SC"))
                base*=2.5;
            else	if(getLocal().getReg().getUf().equals("MG") || getLocal().getReg().getUf().equals("ES") || getLocal().getReg().getUf().equals("MS") || getLocal().getReg().getUf().equals("GO") || getLocal().getReg().getUf().equals("SE") || getLocal().getReg().getUf().equals("PR") || getLocal().getReg().getUf().equals("CE") || getLocal().getReg().getUf().equals("PE") || getLocal().getReg().getUf().equals("AM") || getLocal().getReg().getUf().equals("AM") || getLocal().getReg().getUf().equals("AM"))
                base*=2;
            else
                base*=1.5;
            return ((base/5)+getContrato().getImposto());
	}

// =============== INTERFACE =============
	public final void calcComissao(){
            double valorCompra = getContrato().getCompra().getValor();
            double valorAluguel = getContrato().getAluguel().getValor();
            String texto = "Esse imovel ja esta ocupado ou não habilita compra e aluguel.";
            if((getContrato().getCompra().getHabilitado() || getContrato().getAluguel().getHabilitado()) && !getContrato().getOcupado())
                texto = "Comissao de "+getContrato().getCorretor().getNome()+"\n";
            if(getContrato().getCompra().getHabilitado() && !getContrato().getOcupado())
                texto += "\nComissao de venda: R$"+String.format("%.2f", (valorCompra*0.01));
            if(getContrato().getAluguel().getHabilitado() && !getContrato().getOcupado())
                texto += "\nComissao de aluguel: R$"+String.format("%.2f", (valorAluguel*0.05));
            JOptionPane.showMessageDialog(
                        null,
                        texto,
                        "Verificando comissao",
                        1
            );
	}
	public final double calcParcelar(int parcelas){
            double valor = getContrato().getCompra().getValor();
            return (((valor/parcelas)*1.0114)*parcelas);
	}

	public boolean getCompartilhada(){
            return compartilhada;
	}
	public int getMoradores(){
            return moradores;
	}
	public final void setCompartilhada(boolean compartilhada){
            this.compartilhada = compartilhada;
	}
	public final void setMoradores(int moradores) throws MoradoresPeqException, MoradoresGrdException{
            if(moradores > 0){
                if(moradores <= 3){
                    this.moradores = moradores;
                }else{
                throw new MoradoresGrdException();
                }
            }else{
                throw new MoradoresPeqException();
            }
	}
}