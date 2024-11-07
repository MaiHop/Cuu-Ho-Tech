package com.example.cuu_ho_tech.Presentation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cuu_ho_tech.Presentation.ViewHolder.SearchServiceAndOrderHolder;
import com.example.cuu_ho_tech.Presentation.ViewModel.SharedSearchServiceAndOrderViewModel;
import com.example.cuu_ho_tech.databinding.ItemTextSearchHomeBinding;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

public class SearchServiceAndOrderAdapter extends RecyclerView.Adapter<SearchServiceAndOrderHolder>{
    private List<List<String>> data;
    private Context context;
    private List<List<String>> filteredData;
    private SharedSearchServiceAndOrderViewModel viewModel;
    public SearchServiceAndOrderAdapter(Context context, String[][] data) {
        this.context = context;
        this.data = new ArrayList<>();
        for (String[] row : data) {
            this.data.add(Arrays.asList(row));
        }
        this.filteredData =new ArrayList<>(this.data);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) context).get(SharedSearchServiceAndOrderViewModel.class);
        viewModel.getTextFromEditText().observe((LifecycleOwner) context, new Observer<String>() {
            @Override
            public void onChanged(String inputText) {
                updateFilteredData(inputText);
            }
        });
        sortData();
    }

    @NonNull
    @Override
    public SearchServiceAndOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemTextSearchHomeBinding binding = ItemTextSearchHomeBinding.inflate(inflater, parent, false);
        return new SearchServiceAndOrderHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchServiceAndOrderHolder holder, int position) {
        List<String> texts = filteredData.get(position);
        holder.bind(texts);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setTextFraFromAct(texts.get(0));
            }
        });
    }

    public static String removeDiacritics(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    private void updateFilteredData(String inputText) {
        List<List<String>> newFilteredData = new ArrayList<>();
        String normalizedInput = removeDiacritics(inputText.toLowerCase());

        for (List<String> texts : data) {
            if (removeDiacritics(texts.get(0).toLowerCase()).contains(normalizedInput)) {
                newFilteredData.add(texts);
            }
        }

        if (!filteredData.equals(newFilteredData)) {
            // Track changes to avoid index issues
            int oldSize = filteredData.size();
            int newSize = newFilteredData.size();

            // Loop for items that are the same in both lists
            for (int i = 0; i < Math.min(oldSize, newSize); i++) {
                if (!filteredData.get(i).equals(newFilteredData.get(i))) {
                    filteredData.set(i, newFilteredData.get(i));
                    notifyItemChanged(i);
                }
            }

            // Add new items
            for (int i = oldSize; i < newSize; i++) {
                filteredData.add(newFilteredData.get(i));
                notifyItemInserted(filteredData.size() - 1);
            }

            // Remove old items
            for (int i = oldSize - 1; i >= newSize; i--) {
                filteredData.remove(i);
                notifyItemRemoved(i);
            }
            sortData();
        }
    }
    private void sortData() {
        filteredData.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return filteredData.size();
    }
}
