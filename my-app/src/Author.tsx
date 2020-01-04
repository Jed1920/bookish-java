import React, { useState, useEffect } from 'react'
import { BookModel, BookCard } from './BookModel';
import { fetchApiAuthor } from './FetchApi';

interface AppProps{
    authentication :boolean;
    author :string;
}

interface AuthorCardProps{
    book :BookModel;
    click :number;
}

export function Author(props :AppProps){
    console.log(props.author)
    const [bookList, setBookList] = useState<BookModel[]>([]);
    const [click, setClick] = useState(0)
    let url :string = "http://localhost:8080/author/getBooks"

    useEffect(() =>{
        fetchApiAuthor(url , props.author).then(data => setBookList(data))
    }, [])

    if(bookList.length === 0){
        return <div>loading</div>
    } else {
    return (
        <div className='container'>
            {bookList.map(book =>  <div onClick={() =>setClick(book.id)}><AuthorCard  book={book} click ={click} /></div>)}
        </div>
        )
    }
}

export function AuthorCard(props :AuthorCardProps){

    if(props.click === props.book.id){
        return(
        <div className='cardSelected'>
            <p>{props.book.id}</p>
            <p>{props.book.title}</p>
            <p>{props.book.copyIds.map(id => <p>{id}</p>)}</p>
        </div>
        )
        
    } else {
        return (
        <div className='card'>
            <p>{props.book.id}</p>
            <p>{props.book.title}</p>
        </div>
        )
    }
}