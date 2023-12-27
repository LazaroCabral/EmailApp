
export class UrlQuery{
    
    constructor(url){
        this.url=url;
    }

    urlWithQuery(page,size,name,cpf) {
        let url=this.url;
        let and='';

        if(page!=null) {
            url=url+and+'page='+page;
            and='&';
        }

        if(size!=null) {
            url=url+and+'size='+size;
            and='&';
        }

        if(name!=null) {
            url=url+and+'name='+name;
            and='&';
        }

        if(cpf!=null) {
            url=url+and+'cpf='+cpf;
        }

        return url;
    }
}