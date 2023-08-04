package br.com.erudio;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.converters.NumberConverter;
import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.math.SimpleMath;

@RestController
public class MathController {

  private SimpleMath math = new SimpleMath();

  @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method=RequestMethod.GET)
  public Double sum(
    @PathVariable(value = "numberOne") String numberOne,
    @PathVariable(value = "numberTwo") String numberTwo
  ) throws Exception {

    if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Please, set a numeric value");
    }
    return math.sum(
      NumberConverter.convertToDouble(numberOne),
      NumberConverter.convertToDouble(numberTwo)
    );
  }

  @RequestMapping(value = "/subtraction/{numberOne}/{numberTwo}", method=RequestMethod.GET)
  public Double subtraction(
    @PathVariable(value = "numberOne") String numberOne,
    @PathVariable(value = "numberTwo") String numberTwo
  ) throws Exception {

    if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Please, set a numeric value");
    }
    return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
  }

  @RequestMapping(value = "/average/{numberOne}/{numberTwo}", method=RequestMethod.GET)
  public Double average(
    @PathVariable(value = "numberOne") String numberOne,
    @PathVariable(value = "numberTwo") String numberTwo
  ) throws Exception {

    if (!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Please, set a numeric value");
    }
    return math.average(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo));
  }
}
