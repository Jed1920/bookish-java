import React, { useState, useEffect } from 'react'
import {fetchApiBooks} from './FetchApi'
import {BookModel , BookCard} from'./BookModel'
import { Redirect } from 'react-router-dom';

interface AppProps{
    authentication :boolean
    setAuthor :(author:string) => void
}

export function Book(props :AppProps) {
    
        const [bookList, setBookList] = useState<BookModel[]>([]);
        const [click, setClick] = useState(0)

        let url :string  = "http://localhost:8080/books/page1"
    
        useEffect(() =>{
            if(props.authentication){
            fetchApiBooks(url).then(parsedData => setBookList(parsedData));
            }
        }, []);
        if(!props.authentication){
            return <Redirect to ="./login"/>
        } else if(bookList.length === 0){
            return <div>loading</div>
        } else {
        return (
            <div className='container'>
                {bookList.map(book => <div onClick={() =>setClick(book.id)}> <BookCard  book={book} click ={click} setAuthor ={props.setAuthor}/></div>)}
            </div>
            )       
    }
}