package bd.dbos;

public class Shopping implements Cloneable {
    private String cep;
    private int numero;
    private String nome;
    
    public void setCep(String cep) throws Exception {
        if (cep == null) throw new Exception("Cep não fornecido");
        if (cep.length() > 8 || cep.length() < 8) throw new Exception("CEP com tamanho inválido");

        this.cep = cep;
    }

    public void setNumero(int numero) throws Exception {
        if (numero <= 0) throw new Exception("Número negativo");
        if ((numero + "").length() == 0) throw new Exception("Número nulo");

        this.numero = numero;
    }

    public void setNome(String nome) throws Exception {
        if (nome == null || nome.equals("")) throw new Exception("Nome não fornecido");

        this.nome = nome;
    }

    public String getCep() { return this.cep; }

    public int getNumero() { return this.numero; }

    public String getNome() { return this.nome; }

    public Shopping(String cep, int numero, String nome) throws Exception {
        this.setCep(cep);
        this.setNome(nome);
        this.setNumero(numero);
    }

    public String toString() {
        String ret = "";

        ret += "idCEP: " + this.cep + "\n";
        ret += "idNome: " + this.numero + "\n";
        ret += "idNumero: " + this.nome;

        return ret;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Shopping)) return false;

        Shopping shopping = (Shopping) obj;

        if (this.cep != shopping.cep) return false;
        if (this.numero != shopping.numero) return false;
        if (this.nome.equals(shopping.nome)) return false;

        return true;
    }

    public int hashCode() {
        int ret = 3;

        ret = 3 * ret + this.cep.hashCode();
        ret = 3 * ret + Integer.valueOf(this.numero).hashCode();
        ret = 3 * ret + this.nome.hashCode();

        return ret;
    }

    public Shopping(Shopping carro) throws Exception {
        this.cep = carro.cep;
        this.numero = carro.numero;
        this.nome = carro.nome;
    }

    public Object clone() {
        Shopping ret = null;

        try {
            ret = new Shopping(this);
        }
        catch (Exception erro) {
        }

        return ret;
    }
}
