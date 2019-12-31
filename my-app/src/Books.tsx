import React, { useState, useEffect } from 'react'
import {fetchApi} from './FetchApi'
import {BookModel} from'./BookModel'

export const Book:React.FC = () => {


    const [bookList, setBookList] = useState<BookModel[]>([]);


    let url :string  = "http://localhost:8080/books/page1"
   
    useEffect(() =>{
        fetchApi(url).then(parsedData => setBookList(parsedData));
    }, []);
    
    if(bookList.length === 0){
        return <div>loading</div>
    } else {
    return <div>{bookList}</div>
    }
}