import React from 'react';
import BonCommandeDetail from './BonCommandeDetail';

export default class BonCommandeForm extends React.Component {
  constructor(props) {    
    super(props);    
    this.state = { details: [] , components:[], varArticle:[]};
  }

  fetchApiData = async () => {
    const apiUrl = 'http://localhost:8080/varianteArticle'; // Replace with your actual API endpoint

    try {
      const response = await fetch(apiUrl);

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      let result = await response.json();
      let resultV2 = [];
      for(let i = 0;i<result.length;i++){
          resultV2.push({varArticle:result[i]});
      }
      //this.setState({ apiData: result });
      this.setState((state) => ({
        varArticle: resultV2.map(item => item.varArticle),
      }));
      console.log(this.state.varArticle);
    } catch (error) {
      console.error('Error fetching API data:', error.message);
    }
  };
  

  componentDidMount() {
    this.fetchApiData();
  }

  addDetails = () => {
    this.setState((state) => ({
      details: [...state.details, { id: state.details.length }],
    }));
  };

  handleDetailSubmit = async () => {
    // Collect data from all BonCommandeDetail components
    const detailsData = this.state.details.map((detail) => {
      // Access the state data of each BonCommandeDetail component
      //console.log(this.refs[`detail_${detail.id}`].state.data);
      return this.refs[`detail_${detail.id}`].state.data;
    });

    // Do something with the collected data
    //console.log(JSON.stringify(detailsData));
    //let formadata = {header:{idProforma:"5"},details:detailsData};
    //console.log(JSON.stringify(formadata));
    // You can now send the data to an API, perform further processing, etc.
    const url = 'http://localhost:8080/bonCommande'; // Replace with your actual API endpoint

    const data = {
      //key1: 'value1',
      //key2: 'value2',
      // Add more key-value pairs as needed
      header:{idProforma:"5"},details:detailsData
    };
    
    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          //'Access-Control-Allow-Origin':'http://localhost:3000',
          // Add any other headers your API requires
        },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const result = await response.json();
      console.log('Success:', result);
    } catch (error) {
      console.error('Error:', error.message);
    }
  };

  render() {
    return (
      <>
        <form>
          <table border="1px">
            <thead>
              <tr>
                <th>idVarianteArticle</th>
                <th>quantité voulue</th>
                <th>prix unitaire</th>
                <th>tva</th>
                <th>prix unitaire TTC</th>
              </tr>
            </thead>
            <tbody>
              {this.state.details.map((detail) => (
                <BonCommandeDetail key={detail.id} id={detail.id} ref={`detail_${detail.id}`} varArticle={this.state.varArticle}/>
              ))}
            </tbody>
          </table>
          <button type="button" onClick={this.addDetails}>
            Ajouter article
          </button>
          <button type="button" onClick={this.handleDetailSubmit}>
            Submit
          </button>
        </form>
      </>
    );
  }
}
