import React from 'react'
import { BookModel } from './BookModel'

export const fetchApi = async (url :string) :Promise<BookModel[]> => {
    const response = await fetch(url);
    const json = await response.json();
    console.log(json)
    return parseData(await json);
}

function parseData(jsonApi :any) :BookModel []{
    const books :BookModel [] = [];
    console.log(jsonApi.length)
    jsonApi.forEach((element :any) => {
        let book :BookModel= new BookModel(element['id'],element['title'],element['author']);
        books.push(book);
    });
    return books
}