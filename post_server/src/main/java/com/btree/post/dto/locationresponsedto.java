package com.btree.post.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class locationresponsedto {
    public Document[] documents;
    @Data
    public static class Document {
        public String address_name;
        public double x;
        public double y;
    }
}
