package com.ciandt.summit.bootcamp2022.DTO;

import com.ciandt.summit.bootcamp2022.entity.Music;

import java.util.List;

public class ObjectDTO {
    private List<Music> data;

    public ObjectDTO(List<Music> data) {
        this.data = data;
    }

    public ObjectDTO() {
    }

    public static ObjectDTOBuilder builder() {
        return new ObjectDTOBuilder();
    }

    public List<Music> getData() {
        return this.data;
    }

    public void setData(List<Music> data) {
        this.data = data;
    }

    public String toString() {
        return "ObjectDTO(data=" + this.getData() + ")";
    }

    public static class ObjectDTOBuilder {
        private List<Music> data;

        ObjectDTOBuilder() {
        }

        public ObjectDTOBuilder data(List<Music> data) {
            this.data = data;
            return this;
        }

        public ObjectDTO build() {
            return new ObjectDTO(data);
        }

        public String toString() {
            return "ObjectDTO.ObjectDTOBuilder(data=" + this.data + ")";
        }
    }
}
