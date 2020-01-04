import React from 'react'
import { BookModel } from './BookModel'

export const fetchApiBooks = async (url :string) :Promise<BookModel[]> => {
    const response = await fetch(url);
    const json = await response.json();
    console.log(json);
    return parseData(await json);
}

export const fetchApiAuthor = async (url :string, author :string) :Promise<BookModel[]> => {
    console.log(author)
    const formData = new FormData();
    formData.append("author", author);
    const response = await fetch(url,{
        method:'POST',
        body: formData,
    });
    console.log(response);
    const json = await response.json();
    
    return parseData(await json);
}

export const fetchApiCreateUser = async (url :string, formData :FormData) :Promise<boolean> => {
    
    const response = await fetch(url,{
        method:'POST',
        body: formData,
    });
    console.log(response);
    const json = await response.json();
    
    return await json;
}

function parseData(jsonApi :any) :BookModel []{
    const books :BookModel [] = [];
    console.log(jsonApi.length)
    jsonApi.forEach((element :any) => {
        const book :BookModel= {
            id:element.id,
            title:element.title,
            author:element.author,
            copyIds:element.copyIds
        };
        console.log(book.copyIds);
        books.push(book);
    });
    return books;
}