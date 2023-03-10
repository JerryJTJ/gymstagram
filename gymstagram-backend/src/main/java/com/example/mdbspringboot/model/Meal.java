package com.example.mdbspringboot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Meal")
public class Meal {



		@Id
		private String id;

		private int calories;
		private int carbs;
		private int protein;
        private int fat;
		
		public Meal(String id, int calories, int carbs, int protein, int fat) {
			super();
			this.id = id;
			this.calories = calories;
			this.carbs = carbs;
            this.protein = protein;
            this.fat = fat;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public int getCalories() {
			return calories;
		}

		public void setCalories(int calories) {
			this.calories = calories;
		}

		public int getCarbs() {
			return carbs;
		}

		public void setCarbs(int carbs) {
			this.carbs = carbs;
		}

        public int getProtein() {
            return protein;
        }
        
        public void setProtein(int protein) {
            this.protein = protein;
        }

        public int getFat() {
            return fat;
        }

        public void setFat(int fat) {
            this.fat = fat;
        }
}