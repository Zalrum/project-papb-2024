package com.example.progresss;

public class AuctionItem {
    private String Nama;
    private String image_url;
    private String Harga;

    public AuctionItem(String name, String imageUrl, String harga) {
        this.Nama = name;
        this.image_url = imageUrl;
        this.Harga = harga;
    }


    // Getter dan setter
    public String getName() { return Nama; }
    public void setName(String name) { this.Nama = name; }

    public String getImageUrl() { return image_url; }
    public void setImageUrl(String imageUrl) { this.image_url = imageUrl; }

    public String getHarga() { return Harga; }
    public void setHarga(String harga) { this.Harga = harga; }
}
