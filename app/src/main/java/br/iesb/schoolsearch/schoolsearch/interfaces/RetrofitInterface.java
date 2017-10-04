package br.iesb.schoolsearch.schoolsearch.interfaces;

import java.util.List;

import br.iesb.schoolsearch.schoolsearch.models.EscolaModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    String ENDPOINT = "http://mobile-aceite.tcu.gov.br:80/nossaEscolaRS/";

    @GET("rest/escolas")
    Call<List<EscolaModel>> callListEscolas();

}
