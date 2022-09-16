package com.ciandt.summit.bootcamp2022.utils;

public class LogMassage {

        private LogMassage() {
        }

        public static final String FIND_USER_BY_ID = "Buscando usuario por id.";

        public static final String FIND_ALL_USERS = "Buscando todos os usuários.";

        public static final String FIND_PLAYLIST_BY_ID = "Buscando playlist por id.";

        public static final String FINDING_ALL_PLAYLIST = "Buscando todas as playlists.";

        public static final String FINDING_MUSIC_BY_ID = "Buscando música por id.";

        public static final String FINDING_MUSIC_BY_FILTER = "Buscando músicas com o filtro.";

        public static final String RETURNING_MUSIC_BY_FILTER = "Retornando músicas com o filtro.";
        public static final String FINDING_ALL_MUSICS = "Buscando todas as músicas.";

        public static final String ADD_MUSIC_INTO_PLAYLIST = "Adicionando musica na playlist";

        public static final String ADD_MUSIC_INTO_PLAYLIST_SUCCESSFULL = "Música adicionada na playlist COM SUCESSO";

        public static final String MUSIC_EXISTS_ON_PLAYLIST = "Música já existe na playlist";
        public static final String SAVING_PLAYLIST_INTO_DATA_BASE = "Salvando playlist no banco de dados";

        public static final String CHECKING_MUSIC_EXISTS_INTO_PLAYLIST = "verificando se música existe na playlist";

        public static final String MUSIC_REMOVED_INTO_PLAYLIST = "Música removida da playlist";

        public static final String LOG_ERROR_MUSIC_NOT_FOUND = "Música não existe na playlist, lançando exceção";

        public static final String LOG_ERROR_INVALID_ID = "Id nulo ou em branco";

        public static final String LOG_ERROR_ID_NOT_FOUND = "Id não consta na base de dados";

        public static final String LOG_ERROR_INVALID_PAYLOAD = "Payload da música passado de forma incorreta";

        public static final String LOG_ERROR_EMPTY_LIST = "Lista vazia para busca.";

        public static final String LOG_ERROR_INVALID_FILTER = "Filtro com menos de 3 caracteres";
}
