public class CalculadoraLogica {

    private double resultadoActual;
    private String operacionPendiente;
    private boolean reiniciarOperacion;

    public CalculadoraLogica() {
        resultadoActual = 0;
        operacionPendiente = "";
        reiniciarOperacion = false;
    }

    public double operar(String operacion, double valor) {
        if (reiniciarOperacion) {
            resultadoActual = valor;
            reiniciarOperacion = false;
        } else {
            switch (operacionPendiente) {
                case "+":
                    resultadoActual += valor;
                    break;
                case "-":
                    resultadoActual -= valor;
                    break;
                case "X":
                    resultadoActual *= valor;
                    break;
                case "/":
                    if (valor != 0) {
                        resultadoActual /= valor;
                    } else {
                        throw new ArithmeticException("División por cero");
                    }
                    break;
                default:
                    resultadoActual = valor;
            }
        }
        operacionPendiente = operacion;
        return resultadoActual;
    }

    public void reiniciar() {
        resultadoActual = 0;
        operacionPendiente = "";
        reiniciarOperacion = true;
    }

    public double getResultadoActual() {
        return resultadoActual;
    }
}
