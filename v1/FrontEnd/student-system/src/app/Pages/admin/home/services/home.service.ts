import { Injectable } from '@angular/core';
import Chart from 'chart.js/auto';
import { map } from 'rxjs';
import { HomeApiService } from '../api/home-api.service';

/**
 * Service for generating the users' role quantity graphic.
 */
@Injectable({
  providedIn: 'root',
})
export class HomeService{

  /**
   * Constructs a new instance of the UserApiService.
   * @param {HomeApiService} homeApiService - The HomeApiService instance.
   */
  constructor(private homeApiService: HomeApiService) {}

  /**
   * Generates the users' role quantity graphic using Chart.js.
   * @param {string} component - The ID or class name of the component to render the chart.
   */
  generateUsersRoleQtdGraphic(component: string) {

    let roleDataExtracted: any;
    let qtdDataExtracted: any;
    let extractedData: any;

    extractedData = this.homeApiService
    .getUserQtdRolesData()
    .pipe(
      map((responseData) => {
        return responseData.map(
          (responseData) => {
            return { ...responseData 
          };
        });
      })
    )
    .subscribe(
      (responseData) => {
        console.log(responseData);
        roleDataExtracted = responseData.map((data) => data.role);
        qtdDataExtracted = responseData.map((data) => data.qtd);

        console.log(roleDataExtracted);
        console.log(responseData);
        return new Chart(component, {
          type: 'bar',
          data: {
            labels: roleDataExtracted,
            datasets: [
              {
                label: '# of users',
                data: qtdDataExtracted,
                backgroundColor: [
                  'rgba(0, 255, 0, 0.2)',
                ],
                borderColor: [
                  'rgba(0, 255, 0, 1)',
                ],
                borderWidth: 1,
              },
            ],
          },
          options: {
            plugins: {
              title: {
                text: 'Quantity of users by role',
                align: 'center',
                display: true,
                color: '#fff',
              },
            },
          },
          
        });
      },
      (error) => {
        console.log(error);
        roleDataExtracted = [
          'ERROR',
        ];
        qtdDataExtracted = [0];
        return new Chart(component, {
          type: 'bar',
          data: {
            labels: roleDataExtracted,
            datasets: [
              {
                label: '# of users',
                data: qtdDataExtracted,
                backgroundColor: [
                  'rgba(0, 255, 0, 0.2)',
                ],
                borderColor: [
                  'rgba(0, 255, 0, 1)',
                ],
                borderWidth: 1,
              },
            ],
          },
          options: {
            plugins: {
              title: {
                text: 'Quantity of users by role',
                align: 'center',
                display: true,
                color: '#fff',
              },
            },
          },
          
        });
      }
    );

  }

}
