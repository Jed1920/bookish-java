import React from 'react'
import './BookCard.scss';
import { Link } from 'react-router-dom';


export type BookModel = {
    id :number;
    title :string;
    author :string;
    copyIds :number[];
}

interface BookModelProps{
    book :BookModel;
    click :number;
    setAuthor :(author:string) => void
}

export function BookCard(props :BookModelProps){

    if(props.click === props.book.id){
        return(
        <div className='cardSelected'>
            <p>{props.book.id}</p>
            <p>{props.book.title}</p>
            <Link to ={"/author/" + props.book.author} onClick ={() => props.setAuthor(props.book.author)}>{props.book.author}</Link>
            <p>{props.book.copyIds.map(id => <p>{id}</p>)}</p>
        </div>
        )
        
    } else {
        return (
        <div className='card'>
            <p>{props.book.id}</p>
            <p>{props.book.title}</p>
            <Link to ={"/author/" + props.book.author} onClick ={() => props.setAuthor(props.book.author)}>{props.book.author}</Link>
        </div>
        )
    }
}