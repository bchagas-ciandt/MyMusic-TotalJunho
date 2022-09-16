package com.ciandt.summit.bootcamp2022.utils;

public abstract class ErrorMassage {

    private ErrorMassage() {
    }

    public static final String USER_NOT_FOUND_EXCEPTION = "Usuário não encontrado na base de dados.";

    public static final String MUSIC_NOT_FOUND_EXCEPTION = "Música não encontrada na base de dados.";
    public static final String PLAYLIST_NOT_FOUND_EXCEPTION = "Playlist não encontrada na base de dados.";

    public static final String PLAYLIST_DOES_NOT_BELONG_TO_USER = "Não existe essa playlist no perfil do usuário";

    public static final String MUSIC_NOT_FOUND_INTO_PLAYLIST = "Música não existe na playlist";

    public static final String INVALID_ID_EXCEPTION = "Id não não pode ser nulo ou branco";

    public static final String EMPTY_LIST_EXCEPTION = "Retorno de lista vazia para busca";

    public static final String MUSIC_EXISTS_ON_PLAYLIST_EXCEPTION = "Música já existe na playlist";

    public static final String MUSIC_LIMIT_REACHED_EXCEPTION = "Você atingiu o número máximo de músicas em sua playlist. Para adicionar mais músicas contrate o plano Premium.";

    public static final String PAYLOAD_INVALID_EXCEPTION = "Payload incorreto: Atributo inválido";

    public static final String INVALID_FILTER_EXCEPTION = "Filtro com menos de 3 caracteres";

}
