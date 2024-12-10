/**
 * La clase CalculadoraLogica representa la lógica detrás de las operaciones de
 * una calculadora.
 * Se encarga de realizar cálculos aritméticos (suma, resta, multiplicación,
 * división)
 * y de gestionar el estado de la operación y el resultado actual.
 */
public class CalculadoraLogica {

    private double resultadoActual;
    private String operacionPendiente;
    private boolean reiniciarOperacion;

    /**
     * Constructor de la clase CalculadoraLogica. Inicializa el resultado a 0,
     * no hay operación pendiente, y la calculadora no requiere reiniciar.
     */
    public CalculadoraLogica() {
        resultadoActual = 0;
        operacionPendiente = "";
        reiniciarOperacion = false;
    }

    /**
     * Realiza una operación aritmética sobre el resultado actual y un valor dado.
     * La operación a realizar es determinada por la operación pendiente.
     * 
     * @param operacion la operación a realizar, puede ser "+", "-", "X", o "/".
     * @param valor     el valor sobre el que se aplica la operación.
     * @return el resultado actual después de la operación.
     * @throws ArithmeticException si se intenta realizar una división por cero.
     */
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
                        throw new ArithmeticException("División por cero no permitida.");
                    }
                    break;
                default:
                    resultadoActual = valor;
            }
        }
        operacionPendiente = operacion;
        return resultadoActual;
    }

    /**
     * Reinicia el estado de la calculadora, estableciendo el resultado a 0.
     */
    public void reiniciar() {
        resultadoActual = 0;
        operacionPendiente = "";
        reiniciarOperacion = true;
    }

    /**
     * Agrega un número al resultado actual, multiplicando el valor actual por 10 y
     * sumando el número dado.
     * Esto se utiliza para construir números de varios dígitos.
     * 
     * @param numero el número a agregar al resultado actual.
     */
    public void agregarNumero(double numero) {
        if (reiniciarOperacion) {
            resultadoActual = numero;
            reiniciarOperacion = false;
        } else {
            resultadoActual = resultadoActual * 10 + numero;
        }
    }

    /**
     * Obtiene el resultado actual de la operación en curso.
     * 
     * @return el resultado actual.
     */
    public double getResultadoActual() {
        return resultadoActual;
    }

    /**
     * Prepara la calculadora para iniciar una nueva operación,
     * indicando que el próximo número ingresado debe reemplazar el resultado
     * actual.
     */
    public void prepararOperacion() {
        reiniciarOperacion = true;
    }
}
