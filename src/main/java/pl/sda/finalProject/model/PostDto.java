package pl.sda.finalProject.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class PostDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date createDate;
    private String text;
    private Date modifyDate;
    private Date deleteDate;
    private UserDto userDto;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
