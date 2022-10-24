package br.com.afett.aikidoapi.services;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * Executa cenarios diversos do cucumber.
 * 
 * Cada tag abaixo(opção tags na anotação CucumberOptions) se refere a um
 * cenário definido na feature(opção features na anotação CucumberOptions).
 * 
 * Para excluir a execução de algum cenário(tag) basta comentar a respectiva
 * linha.
 * 
 * http://mkolisnyk.github.io/cucumber-reports/
 * 
 * 
 * MUDAR O LOGIN NA BEFORECLASS
 * 
 * @author andre.fettermann - TRT1/DG/SST/CITQ/DITES
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {

		//"src/test/resources/autenticacao/entrar.feature"
		//"src/test/resources/imoveis/consulta_imoveis.feature"
		//"src/test/resources/imoveis/inclui_imovel.feature"
		//"src/test/resources/imoveis/edita_imovel.feature"
		"src/test/resources/validate_student_promotion.feature"

}
//, tags = "@cenario04"
, plugin = {"pretty"
//		, "html:target/report/cucumber.html"
		, "json:target/report/cucumber.json"}
)
public class RunCukes {
}
